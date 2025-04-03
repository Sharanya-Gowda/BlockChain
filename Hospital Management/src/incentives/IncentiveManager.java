package incentives;

import auth.AuthNode;

public class IncentiveManager {
    private AuthNode authNode;

    public IncentiveManager(AuthNode authNode) {
        this.authNode = authNode;
    }

    public void rewardDoctor(String doctorUsername) {
        authNode.rewardUser(doctorUsername, 10.0); // Reward doctor with 10 e-cash
    }

    public void rewardDiagnosticCenter(String diagnosticUsername) {
        authNode.rewardUser(diagnosticUsername, 5.0); // Reward diagnostic center with 5 e-cash
    }
}
