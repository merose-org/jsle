/**
 * This class file was automatically generated by jASN1 v1.11.2 (http://www.beanit.com)
 */

package ccsds.sle.transfer.service.fsp.structures;

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

import ccsds.sle.transfer.service.common.pdus.ReportingCycle;
import ccsds.sle.transfer.service.common.types.DeliveryMode;
import ccsds.sle.transfer.service.common.types.Diagnostics;
import ccsds.sle.transfer.service.common.types.ForwardDuStatus;
import ccsds.sle.transfer.service.common.types.IntPosLong;
import ccsds.sle.transfer.service.common.types.IntPosShort;
import ccsds.sle.transfer.service.common.types.IntUnsignedLong;
import ccsds.sle.transfer.service.common.types.ParameterName;
import ccsds.sle.transfer.service.common.types.SpaceLinkDataUnit;
import ccsds.sle.transfer.service.common.types.Time;

public class FspPacketLastProcessed implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] code = null;
	public static class PacketProcessed implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

		public byte[] code = null;
		private PacketIdentification packetIdentification = null;
		private Time processingStartTime = null;
		private FspPacketStatus packetStatus = null;
		
		public PacketProcessed() {
		}

		public PacketProcessed(byte[] code) {
			this.code = code;
		}

		public void setPacketIdentification(PacketIdentification packetIdentification) {
			this.packetIdentification = packetIdentification;
		}

		public PacketIdentification getPacketIdentification() {
			return packetIdentification;
		}

		public void setProcessingStartTime(Time processingStartTime) {
			this.processingStartTime = processingStartTime;
		}

		public Time getProcessingStartTime() {
			return processingStartTime;
		}

		public void setPacketStatus(FspPacketStatus packetStatus) {
			this.packetStatus = packetStatus;
		}

		public FspPacketStatus getPacketStatus() {
			return packetStatus;
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
			codeLength += packetStatus.encode(reverseOS, true);
			
			codeLength += processingStartTime.encode(reverseOS);
			
			codeLength += packetIdentification.encode(reverseOS, true);
			
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
			if (berTag.equals(PacketIdentification.tag)) {
				packetIdentification = new PacketIdentification();
				subCodeLength += packetIdentification.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			else {
				throw new IOException("Tag does not match the mandatory sequence element tag.");
			}
			
			processingStartTime = new Time();
			subCodeLength += processingStartTime.decode(is, berTag);
			subCodeLength += berTag.decode(is);
			
			if (berTag.equals(FspPacketStatus.tag)) {
				packetStatus = new FspPacketStatus();
				subCodeLength += packetStatus.decode(is, false);
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
			if (packetIdentification != null) {
				sb.append("packetIdentification: ").append(packetIdentification);
			}
			else {
				sb.append("packetIdentification: <empty-required-field>");
			}
			
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (processingStartTime != null) {
				sb.append("processingStartTime: ");
				processingStartTime.appendAsString(sb, indentLevel + 1);
			}
			else {
				sb.append("processingStartTime: <empty-required-field>");
			}
			
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (packetStatus != null) {
				sb.append("packetStatus: ").append(packetStatus);
			}
			else {
				sb.append("packetStatus: <empty-required-field>");
			}
			
			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	private BerNull noPacketProcessed = null;
	private PacketProcessed packetProcessed = null;
	
	public FspPacketLastProcessed() {
	}

	public FspPacketLastProcessed(byte[] code) {
		this.code = code;
	}

	public void setNoPacketProcessed(BerNull noPacketProcessed) {
		this.noPacketProcessed = noPacketProcessed;
	}

	public BerNull getNoPacketProcessed() {
		return noPacketProcessed;
	}

	public void setPacketProcessed(PacketProcessed packetProcessed) {
		this.packetProcessed = packetProcessed;
	}

	public PacketProcessed getPacketProcessed() {
		return packetProcessed;
	}

	public int encode(OutputStream reverseOS) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			return code.length;
		}

		int codeLength = 0;
		if (packetProcessed != null) {
			codeLength += packetProcessed.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
			reverseOS.write(0xA1);
			codeLength += 1;
			return codeLength;
		}
		
		if (noPacketProcessed != null) {
			codeLength += noPacketProcessed.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 0
			reverseOS.write(0x80);
			codeLength += 1;
			return codeLength;
		}
		
		throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
	}

	public int decode(InputStream is) throws IOException {
		return decode(is, null);
	}

	public int decode(InputStream is, BerTag berTag) throws IOException {

		int codeLength = 0;
		BerTag passedTag = berTag;

		if (berTag == null) {
			berTag = new BerTag();
			codeLength += berTag.decode(is);
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
			noPacketProcessed = new BerNull();
			codeLength += noPacketProcessed.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			packetProcessed = new PacketProcessed();
			codeLength += packetProcessed.decode(is, false);
			return codeLength;
		}

		if (passedTag != null) {
			return 0;
		}

		throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		if (noPacketProcessed != null) {
			sb.append("noPacketProcessed: ").append(noPacketProcessed);
			return;
		}

		if (packetProcessed != null) {
			sb.append("packetProcessed: ");
			packetProcessed.appendAsString(sb, indentLevel + 1);
			return;
		}

		sb.append("<none>");
	}

}

