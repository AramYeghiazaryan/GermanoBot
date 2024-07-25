package com.telegram.duolingo.service;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;

@Component
public class AudioSystemService {

  public SendAudio sendAudioMessage(long chatId, String audioUrl, String caption, String title) {
    SendAudio sendAudio = new SendAudio();
    return sendAudio
        .setChatId(chatId)
        .setAudio(audioUrl)
        .setCaption("My Caption")
        .setTitle("Audio title");
  }

}
