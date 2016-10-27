package hu.innobyte.checker;

public interface Checker {
    public String check(String word);

    public void init(CheckerInitData checkerInit);
}
