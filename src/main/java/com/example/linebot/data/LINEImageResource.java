package com.example.linebot.data;

import org.springframework.core.io.ByteArrayResource;

public class LINEImageResource extends ByteArrayResource {

    public LINEImageResource(byte[] byteArray) {
        super(byteArray);
    }

    @Override
    public String getFilename() {
        // LINEBOTを通すと、LINE側ですべての画像がJPEGに変換される。
        // そのため、元のファイル名とは異なる擬似のファイル名を設定する。
        return "image.jpg";
    }
}