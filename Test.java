public class Test{

    public static void main(String[] args){
	Rope rope1 = new Rope("");
	for (int i = 0; i<10; i++){
	    rope1 = rope1.concatenar(new Rope("Esto es "+i));
	}

	System.out.println(rope1);
	
    }
}
