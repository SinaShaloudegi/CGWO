import java.util.Random;

/**
 * Created by sina on 7/14/2017.
 */
public class CGWO {

    double X[];
    double C[];
    double A[];
    double d[];
    double fitness[];
    int a;
    int alpha, beta, delta;

    public void startGWO() {
        init();
        run();
    }


    private void init() {
        X = new double[100];
        fitness = new double[100];
        Random rand = new Random();
        for (int i = 0; i < X.length; i++) {
            double rnd = rand.nextDouble();
            X[i] = -4 + rnd * (4 - (-4));
            fitness[i] = fit(X[i]);
        }

        double alphaTemp = Double.MAX_VALUE,
                betaTemp = Double.MAX_VALUE,
                deltaTemp = Double.MAX_VALUE;


        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] < alphaTemp) {
                alphaTemp = fitness[i];
                alpha = i;
            }

        }
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] < betaTemp && i != alpha) {
                betaTemp = fitness[i];
                beta = i;
            }

        }
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] < deltaTemp && i != alpha && i != beta) {
                deltaTemp = fitness[i];
                delta = i;
            }

        }

    }


    private void run() {

        for (int i = 0; i < 100; i++) {
            a = 2 - (i * 2) / 100;
            for (int j = 0; j < X.length; j++) {
                double A1, A2, A3;
                Random random = new Random();
                double r11 = random.nextDouble();
                double r12 = random.nextDouble();
                double r13 = random.nextDouble();
                double r21 = random.nextDouble();
                double r22 = random.nextDouble();
                double r23 = random.nextDouble();
                double D_alpha, D_beta, D_delta;
                double C1, C2, C3;
                C1 = 2 * r21;
                C2 = 2 * r22;
                C3 = 2 * r23;
                D_alpha = Math.abs(C1 * X[alpha] - X[i]);
                D_beta = Math.abs(C2 * X[beta] - X[i]);
                D_delta = Math.abs(C3 * X[delta] - X[i]);
                A1 = (2 * a * r11) - a;
                A2 = (2 * a * r12) - a;
                A3 = (2 * a * r13) - a;
                double X1, X2, X3;
                X1 = Math.abs(X[alpha] - A1 * D_alpha);
                X2 = Math.abs(X[beta] - A2 * D_beta);
                X3 = Math.abs(X[delta] - A3 * D_delta);
                X[i] = (X1 + X2 + X3) / 3;

            }
            for (int j = 0; j < X.length; j++) {
                fitness[j] = fit(X[j]);

            }
            double alphaTemp = Double.MAX_VALUE,
                    betaTemp = Double.MAX_VALUE,
                    deltaTemp = Double.MAX_VALUE;


            for (int j = 0; j < fitness.length; j++) {
                if (fitness[j] < alphaTemp) {
                    alphaTemp = fitness[j];
                    alpha = j;
                }

            }
            for (int j = 0; j < fitness.length; j++) {
                if (fitness[j] < betaTemp && j != alpha) {
                    betaTemp = fitness[j];
                    beta = j;
                }

            }
            for (int j = 0; j < fitness.length; j++) {
                if (fitness[j] < deltaTemp && j != alpha && j != beta) {
                    deltaTemp = fitness[j];
                    delta = j;
                }

            }


        }
        System.out.println(String.format("Final Answer:  %#.3f", X[alpha]));
    }


    private double fit(double x) {
        return x * x - 4 * x + 4;
    }

}
