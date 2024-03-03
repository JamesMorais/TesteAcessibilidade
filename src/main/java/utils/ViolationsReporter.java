package utils;

import com.deque.html.axecore.results.CheckedNode;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;

public class ViolationsReporter {
    public static String buildCustomReport(final Results results){
        int ruleCounter = 1;
        int nodeCounter = 1;
        StringBuilder reportBuilder = new StringBuilder();
        for (Rule rule : results.getViolations()) {
            reportBuilder.append("_________________________________________________________________________________\n")
                    .append(ruleCounter).append(") ").append(rule.getDescription()).append(".").append("\n")
                    .append(rule.getHelpUrl()).append("\n");
            for (CheckedNode checkedNode : rule.getNodes()) {
                reportBuilder.append(ruleCounter).append(".").append(nodeCounter).append(") ")
                        .append(checkedNode.getTarget()).append("\n")
                        .append(checkedNode.getFailureSummary()).append("\n\n");
                nodeCounter++;
            }
            ruleCounter++;
            nodeCounter = 1;
        }
        return reportBuilder.toString();
    }
}
