package com.fci.models;

/**
 * API that is used to save and retrieve patient images that can be patient
 * profile image and can be patient examination images this API is has three
 * fields name => image name.<br>
 * picByte => picture content (i.e image).<br>
 * type => image type that may be png, jpeg, ... etc.<br>
 * thats all image details<br>
 *
 */
public class Images {

	private String name;
	private byte[] picByte;
	private String type;

	public Images(String name, String type, byte[] picByte) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
