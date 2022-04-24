import support.Mixer;

public class ShiftLeftMixer extends Mixer {
    public ShiftLeftMixer(String name) {
        super(name);
    }

    @Override
    public void mix(int[] lightings) {
        int buffor1 = lightings[lightings.length - 1];
        int buffor2;
        for (int i = lightings.length - 1; i > 0; i--) {
            buffor2 = lightings[i - 1];
            lightings[i - 1] = buffor1;
            buffor1 = buffor2;
            if (i == 1) {
                lightings[lightings.length - 1] = buffor1;
            }
        }

    }
}

