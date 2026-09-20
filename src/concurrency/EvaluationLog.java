package concurrency;

public class EvaluationLog {

    private final StringBuffer log;

    public EvaluationLog() {
        log = new StringBuffer();
    }

    public void addLog(String massage) {
        log.append(massage).append(System.lineSeparator()); //.append(System.lineSeparator()) là tự xuống dòng phù hợp với hệ điều hành
    }

    public String getLog() {
        return log.toString();
    }
    
}
