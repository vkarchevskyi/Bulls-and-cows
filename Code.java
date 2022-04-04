package bullscows;

public abstract class Code {
    protected final int SIZE;
    protected final char[] code;

    public Code(final int LENGTH) {
        this.SIZE = LENGTH;
        code = new char[LENGTH];
    }
}
