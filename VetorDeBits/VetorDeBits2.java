import java.util.BitSet;

public class VetorDeBits2 {
    private BitSet vetor;
    
    public VetorDeBits2() {
        vetor = new BitSet();
        vetor.set(0);
    }

    public VetorDeBits2(int n) {
        vetor = new BitSet(n);
        vetor.set(n);
    }

    public VetorDeBits2(byte[] v) {
        vetor = BitSet.valueOf(v);
    }

    public byte[] toByteArray() {
        return vetor.toByteArray();
    }

    public void set(int i) {
        if(i>=vetor.length()-1) {
            vetor.clear(vetor.length()-1);
            vetor.set(i+1);
        }
        vetor.set(i);
    }

    public void clear(int i) {
        if(i>=vetor.length()-1) {
            vetor.clear(vetor.length()-1);
            vetor.set(i+1);
        }
        vetor.clear(i);
    }

    public boolean get(int i) {
        return vetor.get(i);
    }

    public int length() {
        return vetor.length()-1;
    }

    public int size() {
        return vetor.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<vetor.length()-1; i++)
            if(vetor.get(i))
                sb.append('1');
            else
                sb.append('0');
        return sb.toString();
    }

    public static void main(String[] args) {
        VetorDeBits2 vb = new VetorDeBits2(25);
        System.out.println(vb);
        vb.set(2);
        vb.set(20);
        System.out.println(vb);
        vb.clear(70);
        System.out.println(vb);
        System.out.println(vb.length());
        System.out.println(vb.size());

        System.out.println(vb.toByteArray().length);

        VetorDeBits2 vb2 = new VetorDeBits2(vb.toByteArray());
        System.out.println(vb2);
        System.out.println(vb2.length());
        System.out.println(vb2.size());
    }
    
}
