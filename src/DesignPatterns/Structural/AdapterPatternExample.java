package DesignPatterns.Structural;

/*
The Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together.
It acts as a bridge between two incompatible interfaces by converting the interface of one class into another
interface that a client expects.

Let's illustrate the Adapter Pattern with an example of a media player that can play different types of audio files,
including MP3 and WAV files. We'll have an existing MP3 player class and a new WAV player class.
We'll use an adapter to make the WAV player compatible with the media player interface.
* */


// Adaptee interface
interface AdvancedMediaPlayer {
    void playMp3(String fileName);
}

// Concrete Adaptee class
class Mp3Player implements AdvancedMediaPlayer {
    @Override
    public void playMp3(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}


// Adapter class
class MediaAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedMediaPlayer;

    MediaAdapter(AdvancedMediaPlayer advancedMediaPlayer) {
        this.advancedMediaPlayer = advancedMediaPlayer;
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            advancedMediaPlayer.playMp3(fileName);
        } else if (audioType.equalsIgnoreCase("wav")) {
            System.out.println("Playing WAV file: " + fileName);
            // Additional code to play WAV file
        } else {
            System.out.println("Unsupported audio type: " + audioType);
        }
    }
}

// Client class
class AudioPlayer implements MediaPlayer {
    private final MediaAdapter mediaAdapter;

    AudioPlayer() {
        mediaAdapter = new MediaAdapter(new Mp3Player());
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3") || audioType.equalsIgnoreCase("wav")) {
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Unsupported audio type: " + audioType);
        }
    }
}

// Main class
public class AdapterPatternExample {
    public static void main(String[] args) {
        MediaPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("wav", "music.wav");
        audioPlayer.play("mp4", "video.mp4");
    }
}



