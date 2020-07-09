import org.apache.log4j.Logger;

import java.util.Random;

public class Retry {
    private static final Logger LOGGER = Logger.getLogger(Retry.class);
    static int time = 0;
    static int iterationsCount;
    static int attemptsCount;

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        iterationsCount = random.nextInt(10);
        attemptsCount = random.nextInt(10);

        for (int i = 0; i < iterationsCount; i++) {

            try {
                time += 50;
                Thread.sleep(time);
                LOGGER.info("Now we are throwing exception");
                throw new Exception();

            } catch (Exception e) {
                Block block = () -> LOGGER.info("Attempts that we have: " + attemptsCount);

                if (attemptsCount > 0) {
                    block.run();
                    attemptsCount--;
                } else {
                    LOGGER.error("We ran out of attempts, we were unable to execute the program:(");
                    throw e;
                }
            }
        }
        LOGGER.info("Our program throws all expected exception, but we still have attempts! It's success!");
    }
}

