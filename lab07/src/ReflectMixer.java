import support.Mixer;

public class ReflectMixer extends Mixer {
    public ReflectMixer(String name) {
        super(name);
    }

    @Override
    public void mix(int[] lightings) {
        int bufor;
        for (int i = 0; i < lightings.length / 2; i++) {
            bufor = lightings[i];
            lightings[i] = lightings[lightings.length - i - 1];
            lightings[lightings.length - i - 1] = bufor;
        }
    }
}
