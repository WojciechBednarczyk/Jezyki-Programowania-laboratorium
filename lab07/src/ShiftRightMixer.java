import support.Mixer;

public class ShiftRightMixer extends Mixer {
    public ShiftRightMixer(String name) {
        super(name);
    }

    @Override
    public void mix(int[] lightings) {
        int buffor1 = lightings[0];
        int buffor2;
        for (int i = 0; i < lightings.length - 1; i++) {
            buffor2 = lightings[i + 1];
            lightings[i + 1] = buffor1;
            buffor1 = buffor2;
            if (i == lightings.length - 2) {
                lightings[0] = buffor1;
            }
        }

    }
}
