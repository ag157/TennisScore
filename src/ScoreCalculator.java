
public class ScoreCalculator {

	String pointSeq;

	int[][] scores=new int[3][2];
	
	
	public ScoreCalculator(String inputScore) {
		this.pointSeq = inputScore;
	}
	
	static String[] points={"love","15","30","40","advantage"};
	void handleWin(int i){
		scores[1][i] +=1;
		scores[2][0]=0;
		scores[2][1]=0;
		if(scores[1][0]>=6&&(scores[1][0]-scores[1][1])>2){
			scores[1][0]=0;
			scores[1][1]=0;
			scores[0][0]++;
		}
		if(scores[1][1]>=6&&(scores[1][1]-scores[1][0])>2){
			scores[1][0]=0;
			scores[1][1]=0;
			scores[0][1]++;
		}
		
	}
	void print(){
		System.out.println("player:        A        B");
		System.out.println("Sets:          "+scores[0][0]+"        "+scores[0][1]);
		System.out.println("Games:         "+scores[1][0]+"        "+scores[1][1]);
		System.out.println("Points:       "+points[scores[2][0]]+"       "+points[scores[2][1]]);
		System.out.println("\n\n\n\n");
	}



	public Integer handleDeuce(Integer currentIndex) {
		
		String currentSequence = pointSeq.substring(currentIndex+1);
		//System.out.println(currentSequence);
		Integer tmp = 0;
		while (tmp+2 <= currentSequence.length()) {
			
			String tmpSeq = currentSequence.substring(tmp, tmp+2);
			//System.out.println("---"+tmpSeq);
			if (tmpSeq.equals("AA")) {
				handleWin(0);
				return currentIndex + tmp +2;
				
				
			}else if(tmpSeq.equals("BB")) {
				handleWin(1);
				return currentIndex + tmp +2;
			}
			tmp += 2;
			//System.out.println(tmp);
		}
		
		
		return currentIndex + tmp;
	}
	
	
	public void calculateScore(){
		for(int i=0;i<pointSeq.length();i++){
			//System.out.println(i);
			//System.out.println((int)pointSeq.charAt(i)-'A' + " | " +pointSeq.charAt(i));
			scores[2][pointSeq.charAt(i)-'A']++;
			if(scores[2][0]==3 && scores[2][1]==3){
				//System.out.println(i);
				i = handleDeuce(i);
				//System.out.println(i);
			}
			else if((scores[2][0] == 4 && scores[2][1]<3)|| (scores[2][1] == 4 && scores[2][0]<3)){
				handleWin(pointSeq.charAt(i)-'A');
			}
			
		}
	}
	public static void main(String[] args) {
		ScoreCalculator tennisSC = new ScoreCalculator("ABABABABAAAAAAAAAAAAAAAAAAAAAAAAAB");
		//tennisSC.handleDeuce(5);
		tennisSC.calculateScore();
		
		//System.out.println(tennisSC.scores[0][0] + " | " + tennisSC.scores[0][1]);
		//System.out.println(tennisSC.scores[1][0] + " | " + tennisSC.scores[1][1]);
		//System.out.println(tennisSC.scores[2][0] + " | " + tennisSC.scores[2][1]);
		
		tennisSC.print();
	}

}
