import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import aed3.Registro;


public class Rotulo implements Registro {

    // ================== Atributos ==================
    private int id;
    private String rotulo;

    // ================== Construtores ==================
    public Rotulo() {
        this.id = -1;
        this.rotulo = "";
    }

    public Rotulo(int id, String rotulo) {
        this.id = id;
        this.rotulo = rotulo;
    }

    public Rotulo(String rotulo) {
        this.id = -1;
        this.rotulo = rotulo;
    }

    public Rotulo(int id) {
        this.id = id;
        this.rotulo = "";
    }

    // ================== Getters e Setters ==================
    public int getId() {
        return this.id;
    }

    public String getRotulo() {
        return this.rotulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    // ================== Métodos ==================
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(rotulo);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        rotulo = dis.readUTF();
    }

    public String toString() {
        return "\nID:..............: " + this.id + 
             "\nRótulo:............: " + this.rotulo;
    }


    public int compareTo(Object o) {
        return this.id - ((Rotulo)o).id;
    }
}


