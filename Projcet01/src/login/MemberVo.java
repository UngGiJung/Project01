package login;

public class MemberVo {

    private String text;
    private String text2;
    private String text3;
    private String text4;
    
 
    public MemberVo() {
 
    }
    
    public MemberVo(String text) {
        this.text = text;        
    }
 
    public MemberVo(String text, String text2) {
        this.text = text;
        this.text2 = text2;
        
    }
    
    public MemberVo(String text, String text2, String text3) {
        this.text = text;
        this.text2 = text2;
        this.text3 = text3;
        
    }
    
    public MemberVo(String text, String text2, String text3, String text4) {
        this.text = text;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        
    }
 
   public String getWord() {
      return text;
   }
   public String getWord2() {
      return text2;
   }
   public String getWord3() {
	      return text3;
	   }
   public String getWord4() {
	      return text4;
	   }
}
