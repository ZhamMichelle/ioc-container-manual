package org.example;

@Singleton
@Deprecated
public class RecommendatorImpl implements Recommendator {
    @InjectProperty
    private String alcohol;

    public RecommendatorImpl() {
        System.out.println("Created RecommendatorImpl!");
    }

    @Override
    public void recommend() {
        System.out.println("To protect from covid-2019, drink " + alcohol);
    }
}
