import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class JavaSoundDemo {
    public static void main(String[] args) throws Exception {
        float sampleRate = 44100;
        float frequency = 440; // A4
        double durationSeconds = 2.0;

        // Define the audio format: 16-bit, mono, signed PCM
        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);

        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();

        int totalSamples = (int) (sampleRate * durationSeconds);
        byte[] buffer = new byte[totalSamples * 2]; // 2 bytes per sample (16-bit)

        for (int i = 0; i < totalSamples; i++) {
            double angle = 2.0 * Math.PI * i * frequency / sampleRate;
            short sample = (short) (Math.sin(angle) * Short.MAX_VALUE * 0.5); // 0.5 = amplitude

            // Little-endian: low byte first
            buffer[i * 2] = (byte) (sample & 0xFF);
            buffer[i * 2 + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        System.out.println("Playing a 440 Hz tone for 2 seconds...");
        line.write(buffer, 0, buffer.length);

        line.drain();
        line.stop();
        line.close();
    }
}