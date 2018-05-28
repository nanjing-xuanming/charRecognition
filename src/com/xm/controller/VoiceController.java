package com.xm.controller;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.xm.player.AudioPlayer;
import javazoom.jl.decoder.JavaLayerException;
import net.sf.json.JSON;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;

/**
 * 语音合成控制成
 *
 * @author XuanMing
 * @create 2018/5/18 9:28
 **/
@Controller
@RequestMapping(value = "voice")
public class VoiceController {

    private static final String APPID = "11259327";

    private static final String APIKEY = "lAx9waGfUGgSpoRSGw73Rybk";

    private static final String SERCETKEY = "820d92f1bbd8e1256141f6837eb90fe6";

    @RequestMapping(value = "compound")
    @ResponseBody
    private void compound(HttpServletRequest request, @RequestParam String text) throws FileNotFoundException, JavaLayerException {
        String fileName = System.currentTimeMillis() + ".mp3";
        String sysFilePath = request.getSession().getServletContext().getRealPath("/");
        String filePath = sysFilePath + "mp3/";
        AipSpeech aipSpeech = new AipSpeech(APPID, APIKEY, SERCETKEY);
        HashMap<String, Object> options = new HashMap<String, Object>();
        //语速，取值0-9，默认为5中语速
        options.put("spd", "5");
        //音调，取值0-9，默认为5中语调
        options.put("pit", "5");
        //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per", "0");
        TtsResponse ttsResponse = aipSpeech.synthesis(text, "zh", 1, options);
        byte[] bytes = ttsResponse.getData();
        getFile(bytes, filePath, fileName);

        //语音播放
        AudioPlayer player = new AudioPlayer(new File(filePath + fileName));
        player.play();
    }

    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            // 判断文件目录是否存在
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
