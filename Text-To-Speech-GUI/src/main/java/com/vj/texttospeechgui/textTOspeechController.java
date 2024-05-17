package com.vj.texttospeechgui;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;

public class textTOspeechController {
    // It's the Backed Controller that controls the Operations.

    //Provided By THe External Dependencies
    private static VoiceManager voiceManager = VoiceManager.getInstance();

    public static ArrayList<String> getVoices(){
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        ArrayList<String> voices = new ArrayList<>();
        for(Voice voice:voiceManager.getVoices()){
            voices.add(voice.getName());
        }
        return voices;
    }

    // Method for SpeedRates
    public static ArrayList<String> getSpeedRates(){
        ArrayList<String> speedRates = new ArrayList<>();
        speedRates.add("140"); //Normal
        speedRates.add("170"); //fast
        speedRates.add("200"); //Very Fast
        speedRates.add("100"); // Slow
        speedRates.add("60"); // very Slow

        return speedRates;
    }

    //Method for Getting Volumes
    public static ArrayList<String> getVolumeLevels(){

        ArrayList<String> volumeLevels = new ArrayList<>();

        for(int i=0; i<=10; i++){
            volumeLevels.add(Integer.toString(i));
        }

        return volumeLevels;
    }

    public static void speak(String message,String voiceType,String rate,String volume){
        //Voice object to store the voicetype
        Voice voice = voiceManager.getVoice(voiceType);
        if(voice == null){
            System.err.println("Cannot FInd Voice : Kevin16");
            System.exit(1);
        }

        //allocating Resources for Our Voice
        voice.allocate();

        //Set the Speed WPM
        voice.setRate(Integer.parseInt(rate));

        //Convert Text to Speech
        voice.speak(message);

        //Deallocating the voice , When Done
        voice.deallocate();
    }

}