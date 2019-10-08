/**
 * This class file was automatically generated by jASN1 v1.11.2 (http://www.beanit.com)
 */

package ccsds.sle.transfer.service.cltu.incoming.pdus;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import com.beanit.jasn1.ber.*;
import com.beanit.jasn1.ber.types.*;
import com.beanit.jasn1.ber.types.string.*;

import ccsds.sle.transfer.service.bind.types.SleBindInvocation;
import ccsds.sle.transfer.service.bind.types.SlePeerAbort;
import ccsds.sle.transfer.service.bind.types.SleUnbindInvocation;
import ccsds.sle.transfer.service.cltu.structures.CltuData;
import ccsds.sle.transfer.service.cltu.structures.CltuIdentification;
import ccsds.sle.transfer.service.cltu.structures.CltuParameterName;
import ccsds.sle.transfer.service.cltu.structures.EventInvocationId;
import ccsds.sle.transfer.service.common.pdus.SleScheduleStatusReportInvocation;
import ccsds.sle.transfer.service.common.pdus.SleStopInvocation;
import ccsds.sle.transfer.service.common.types.ConditionalTime;
import ccsds.sle.transfer.service.common.types.Credentials;
import ccsds.sle.transfer.service.common.types.Duration;
import ccsds.sle.transfer.service.common.types.IntPosShort;
import ccsds.sle.transfer.service.common.types.InvokeId;
import ccsds.sle.transfer.service.common.types.SlduStatusNotification;

public class CltuStartInvocation implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private Credentials invokerCredentials = null;
	private InvokeId invokeId = null;
	private CltuIdentification firstCltuIdentification = null;
	
	public CltuStartInvocation() {
	}

	public CltuStartInvocation(byte[] code) {
		this.code = code;
	}

	public void setInvokerCredentials(Credentials invokerCredentials) {
		this.invokerCredentials = invokerCredentials;
	}

	public Credentials getInvokerCredentials() {
		return invokerCredentials;
	}

	public void setInvokeId(InvokeId invokeId) {
		this.invokeId = invokeId;
	}

	public InvokeId getInvokeId() {
		return invokeId;
	}

	public void setFirstCltuIdentification(CltuIdentification firstCltuIdentification) {
		this.firstCltuIdentification = firstCltuIdentification;
	}

	public CltuIdentification getFirstCltuIdentification() {
		return firstCltuIdentification;
	}

	public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		codeLength += firstCltuIdentification.encode(reverseOS, true);
		
		codeLength += invokeId.encode(reverseOS, true);
		
		codeLength += invokerCredentials.encode(reverseOS);
		
		codeLength += BerLength.encodeLength(reverseOS, codeLength);

		if (withTag) {
			codeLength += tag.encode(reverseOS);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		codeLength += totalLength;

		subCodeLength += berTag.decode(is);
		invokerCredentials = new Credentials();
		subCodeLength += invokerCredentials.decode(is, berTag);
		subCodeLength += berTag.decode(is);
		
		if (berTag.equals(InvokeId.tag)) {
			invokeId = new InvokeId();
			subCodeLength += invokeId.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(CltuIdentification.tag)) {
			firstCltuIdentification = new CltuIdentification();
			subCodeLength += firstCltuIdentification.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (invokerCredentials != null) {
			sb.append("invokerCredentials: ");
			invokerCredentials.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("invokerCredentials: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (invokeId != null) {
			sb.append("invokeId: ").append(invokeId);
		}
		else {
			sb.append("invokeId: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (firstCltuIdentification != null) {
			sb.append("firstCltuIdentification: ").append(firstCltuIdentification);
		}
		else {
			sb.append("firstCltuIdentification: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

