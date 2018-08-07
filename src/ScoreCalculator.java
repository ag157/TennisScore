
public class ScoreCalculator {

	String pointSeq;

	//int[][] scores=new int[3][2];
	
	Player playerA;
	Player playerB;
	
	public ScoreCalculator(String inputScore) {
		this.pointSeq = inputScore;
		playerA = new Player(0, 0, 0);
		playerB = new Player(0, 0, 0);
	}
	
	static String[] points={"love","15","30","40","advantage"};
	
	void handleWin(int i){
		if (i ==0 ){
			playerA.games += 1;
		} else 
			playerB.games += 1;
		
		playerA.points = 0;
		playerB.points = 0;
		
//		scores[1][i] +=1;
//		scores[2][0]=0;
//		scores[2][1]=0;
//		if(scores[1][0] >= 6&& ( scores[1][0] - scores[1][1] ) > 2){
//			scores[1][0]=0;
//			scores[1][1]=0;
//			scores[0][0]++;
//		}
//		if(scores[1][1]>=6&&(scores[1][1]-scores[1][0])>2){
//			scores[1][0]=0;
//			scores[1][1]=0;
//			scores[0][1]++;
//		}
		
		if (playerA.games >= 6 && (playerA.games - playerB.games) > 2) {
			
			playerA.sets += 1;
			playerA.games = 0;
			playerB.games = 0;
		}
		if (playerB.games >= 6 && (playerB.games - playerA.games) > 2) {
			
			playerB.sets += 1;
			playerA.games = 0;
			playerB.games = 0;
		}
		
	}
	void print(){
		System.out.println("player:        A        B");
		System.out.println("Sets:          " + playerA.sets + "        " + playerB.sets);
		System.out.println("Games:         " + playerA.games + "        " + playerB.games);
	
		if (playerA.points == playerB.points && points[playerA.points].equals("40"))
			System.out.println("Points: DEUCE");
		else
			System.out.println("Points:       " + points[playerA.points] + "       " + points[playerB.points]);
		
		System.out.println("\n\n\n\n");
	}



	public Integer handleDeuce(Integer currentIndex) {
		
		String currentSequence = pointSeq.substring(currentIndex+1);
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
			if (pointSeq.charAt(i) == 'A')
				playerA.points += 1;
			else
				playerB.points += 1;
			//scores[2][pointSeq.charAt(i)-'A']++;
			if(playerA.points ==3 && playerB.points == 3){
				//System.out.println(i);
				i = handleDeuce(i);
				//System.out.println(i);
			}
			else if((playerA.points == 4 && playerB.points<3)|| (playerA.points == 4 && playerB.points<3)){
				
				handleWin(pointSeq.charAt(i)-'A');
			}
			
		}
	}
	
	public ScoreCalculator(Player playA, Player playB) {
		this.playerA = playA;
		this.playerB = playB;
	}
	public static void main(String[] args) {
		ScoreCalculator tennisSC = new ScoreCalculator("ABABABAABAB");

		tennisSC.calculateScore();
		
		//System.out.println(tennisSC.scores[0][0] + " | " + tennisSC.scores[0][1]);
		//System.out.println(tennisSC.scores[1][0] + " | " + tennisSC.scores[1][1]);
		//System.out.println(tennisSC.scores[2][0] + " | " + tennisSC.scores[2][1]);
		
		tennisSC.print();
	}

}
