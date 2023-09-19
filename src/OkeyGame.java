import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;





public class OkeyGame {

//commit1
//commit2
//commit3
	
	
		
	
	
	public static void main(String[] args) {
		String[] gameStones=OkeyGame.createOkeyStones();
		String[] shuffledStones=shuffleStones(gameStones);
		String okey=chooseOkey(shuffledStones);
		String okey1="ege";
		System.out.println("OKEY:"+okey);
		List<List<String>> players=dealStonesToPlayers(shuffledStones);// 4 tane List<String> tipinde kullan�c� d�ner her bir kullan�c�n�n 14 ta�� vard�r(�lk kullan�c� hari�, ilk kullan�c� 15 ta�a sahiptir.)
		
	}
	
	
	
	
	public static String[] createOkeyStones() { 
		String[] stones=new String[106];
		int arrayIndex=0;
		String[] colours= {"sar�","mavi","k�rm�z�","siyah"};
		for(int j=0;j<2;j++){
			for (String cls : colours) {
			for(int i=1;i<14;i++) {
				stones[arrayIndex]=cls+"-"+(i);
				arrayIndex++;
			}
		}
		}
		stones[arrayIndex]="sahte-okey";
		stones[arrayIndex+1]="sahte-okey";
		
		return stones;
	}
	
	public static String[] shuffleStones(String[] stones) {
		List<String> shuffleStones=Arrays.asList(stones);
		Collections.shuffle(shuffleStones);
		shuffleStones.toArray(stones);
		return stones;
	}
	
	public static String chooseOkey(String[] stones) {
		String okey="";
		Random random=new Random();
		int rndIndex=random.nextInt(106);
		String pulledStone=stones[rndIndex];
		String[] colourNumber=pulledStone.split("-");
		String colour=colourNumber[0];
		int number=Integer.parseInt(colourNumber[1]);
		if(!colourNumber[0].equals("sahte")) {
			if(number<13) {
				okey=colour+"-"+(number+1);
				
			}else {
				okey=colour+"-"+(1);//13 g�stergeyse okey 1 olur
			}
			stones[rndIndex]=null; //G�stergenin bir tanesi oyun ta�lar�ndan ��kar�l�r oyunda 1 tane g�sterge kal�r
			
		}else {
			chooseOkey(stones);
		}
		return okey;

	}

	public static List<List<String>> dealStonesToPlayers(String[] stones){
		Random random=new Random();
		List<List<String>> players=new ArrayList<>();
		List<Integer> numbers=IntStream.rangeClosed(0, 105).boxed().collect(Collectors.toList());//random da��tmak i�in index numaralar�
		
		List<String> player=new ArrayList<>();
		for(int i=0;i<4;i++) {
			if(players.size()==0) {
				for(int j=0;j<15;j++) {
					int rnd=random.nextInt(numbers.size());
					String stone=stones[numbers.get(rnd)];
					while(stone==null) { //Ta�lar�n i�inden g�stergenin biri silindi�i i�in ta��n null gelmesinin �n�ne ge�mek i�in bir kontrol
						rnd=random.nextInt(numbers.size());
						stone=stones[numbers.get(rnd)];
					}
					player.add(stone);
					stones[numbers.get(rnd)]=null;
					numbers.remove(rnd);
					
				}
			}else {
				for(int j=0;j<14;j++) {
					int rnd=random.nextInt(numbers.size());
					String stone=stones[numbers.get(rnd)];
					while(stone==null) {
						rnd=random.nextInt(numbers.size());
						stone=stones[numbers.get(rnd)];
					}
					player.add(stone);
					stones[numbers.get(rnd)]=null;
					numbers.remove(rnd);
					
				}
			}
			
			System.out.println(player.toString());
			System.out.println("*****************************************");
			players.add(player);
			player.clear();
		}
		
		
		
		
		return players;
	}
}
