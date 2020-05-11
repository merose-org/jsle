package org.yamcs.sle.udpslebridge;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

import org.yamcs.sle.Constants.ApplicationIdentifier;
import org.yamcs.sle.provider.CltuServiceProvider;
import org.yamcs.sle.provider.CltuUplinker;
import org.yamcs.sle.provider.RafServiceProvider;
import org.yamcs.sle.provider.ServiceInitializer;
import org.yamcs.sle.provider.SleService;

public class BridgeServiceInitializer implements ServiceInitializer {
    final Properties properties;
    static Logger logger = Logger.getLogger(BridgeServiceInitializer.class.getName());

    public BridgeServiceInitializer(Properties properties) {
        this.properties = properties;
    }

    @Override
    public ServiceInitResult getServiceInstance(String initiatorId, String responderPortId, ApplicationIdentifier appId,
            String sii) {
        System.out.println("props: "+properties);
        // look for an entry where auth.x.initiatorId = initiatorId and return x
        Optional<String> x = properties.entrySet().stream()
                .filter(e -> sii.equals(e.getValue())
                        && ((String) e.getKey()).startsWith("service.")
                        && ((String) e.getKey()).endsWith(".sii"))
                .map(e -> {
                    String k = (String) e.getKey();
                    return k.substring(8, k.length() - 4);
                }).findFirst();

        if (!x.isPresent()) {
            logger.info("Cannot find a service entry for sii='" + sii + "'");
            return negativeResponse(3); // no such service instance
        }

        String id = x.get();
        String servType = properties.getProperty("service." + id + ".type");

        if ("raf".equals(servType)) {
            if (appId != ApplicationIdentifier.rtnAllFrames) {
                logger.info("Requested application " + appId + " does not match defined service type raf");
                return negativeResponse(6);// inconsistent service type
            }
            return createRafProvider(id);

        } else if ("cltu".equals(servType)) {
            if (appId != ApplicationIdentifier.fwdCltu) {
                logger.info("Requested application " + appId + " does not match defined service type cltu");
                return negativeResponse(6);// inconsistent service type
            }
            return createCltuProvider(id);
        } else {
            logger.warning("Invalid value for service." + id + ".type: '" + servType + "'");
            return negativeResponse(1);// service type not supported
        }

    }

    private ServiceInitResult createRafProvider(String id) {
        int port = Integer.valueOf(properties.getProperty("service."+id+".udpPort"));
        int maxFrameLength = Integer.valueOf(properties.getProperty("service."+id+".udpPort", "1115"));
        RafServiceProvider rsp = new RafServiceProvider(new UdpFrameReceiver(port, maxFrameLength));
        return positiveResponse("cltu-"+id, rsp);
    }

    private ServiceInitResult createCltuProvider(String id) {
        String hostname = properties.getProperty("service."+id+".udpHost");
        int port = Integer.valueOf(properties.getProperty("service."+id+".udpPort"));
        int bitrate = Integer.valueOf(properties.getProperty("service."+id+".bitrate"));
        CltuUplinker cltuUplinker = new UdpCltuUplinker(hostname, port, bitrate);

        CltuServiceProvider csp = new CltuServiceProvider(cltuUplinker);
        return positiveResponse("raf-"+id, csp);
    }

    private ServiceInitResult positiveResponse(String id, SleService service) {
        ServiceInitResult r = new ServiceInitResult();
        r.success = true;
        r.sleService = service;
        r.name = id;
        return r;
    }

    ServiceInitResult negativeResponse(int diag) {
        ServiceInitResult r = new ServiceInitResult();
        r.success = false;
        r.diagnostic = diag;
        return r;
    }

}