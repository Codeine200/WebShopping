package com.wizardjava.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class ImagesUtils {

      public static String base64Encoded(byte[] content) throws UnsupportedEncodingException {
        Base64 binaryBase64 = new Base64();
        byte[] encodeBase64 = binaryBase64.encode(content);
        return new String(encodeBase64, "UTF-8");
    }
}
