import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;

public class JSynDemo {
    public static void main(String[] args) throws Exception {
        // Create the synthesizer
        Synthesizer synth = JSyn.createSynthesizer();

        // Create a sine wave oscillator and an audio output
        SineOscillator osc = new SineOscillator();
        LineOut lineOut = new LineOut();

        synth.add(osc);
        synth.add(lineOut);

        // Connect oscillator output to both left and right channels
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);

        // Set frequency (A4 = 440 Hz) and amplitude
        osc.frequency.set(440.0);
        osc.amplitude.set(0.5);

        // Start the synth and audio output
        synth.start();
        lineOut.start();

        System.out.println("Playing a 440 Hz tone for 2 seconds...");
        synth.sleepFor(2.0);

        // Stop everything
        lineOut.stop();
        synth.stop();
    }
}