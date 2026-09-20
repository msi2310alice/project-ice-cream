package model.trial;

public class SensoryEvaluation {
    private String idEvaluation;
    private String nameEvaluator;
    private int tasteScore;
    private int textureScore;
    private int meltingScore;
    
    public SensoryEvaluation(String idEvaluation, String nameEvaluator, int tasteScore, int textureScore, int meltingScore) {
        this.idEvaluation = idEvaluation;
        this.nameEvaluator = nameEvaluator;
        // range score 1=>5. 1 = Dislike Very Much, 2 = Dislike, 3 = Neither Like nor Dislike, 4 = Like, 5 = Like Very Much
        this.tasteScore = tasteScore;
        this.textureScore = textureScore;
        this.meltingScore = meltingScore;
    }

    public String getIdEvaluation() {
        return idEvaluation;
    }
    public String getNameEvaluator() {
        return nameEvaluator;
    }
    public int getTasteScore() {
        return tasteScore;
    }
    public int getTextureScore() {
        return textureScore;
    }
    public int getMeltingScore() {
        return meltingScore;
    }

    public void setNameEvaluator(String name) {
        this.nameEvaluator = name;
    }
    public void setTasteScore(int tasteScore) {
        this.tasteScore = tasteScore;
    }
    public void setTextureScore(int textureScore) {
        this.textureScore  = textureScore;
    }
    public void setMeltingScore(int meltingScore) {
        this.meltingScore = meltingScore;
    }

    // FIXED: average must include meltingScore, not textureScore twice
    public double getAverageScore() {
        return (double)(tasteScore + textureScore + meltingScore)/3;
    }

    @Override 
    public String toString() {
        return "SensoryEvaluation{ " 
                + "idEvaluation= " + idEvaluation
                + ", nameEvaluator= " + nameEvaluator
                + ", tasteScore= " + tasteScore
                + ", textureScore= " + textureScore
                + ", meltingScore= " + meltingScore
                + "averageScore= " + getAverageScore()
                + "}";
    }
}
