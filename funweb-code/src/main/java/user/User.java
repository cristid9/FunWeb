package user;

public class User {
    private long id;
    private String name;
    private String userRole;
    private String email;
    private String loginType;
    private int level;
    private int hintsLeft;
    private int goldLeft;
    private String avatarPath;

    public User(){

    }

    public User(long id, String name, String userRole, String eMail, String loginType, int level, int hintsLeft, int goldLeft, String avatarPath){
        this.id = id;
        this.name = name;
        this.userRole = userRole;
        this.email = eMail;
        this.loginType = loginType;
        this.level = level;
        this.hintsLeft = hintsLeft;
        this.goldLeft = goldLeft;
        this.avatarPath = avatarPath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHintsLeft(int hintsLeft) {
        this.hintsLeft = hintsLeft;
    }

    public void setGoldLeft(int goldLeft) {
        this.goldLeft = goldLeft;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getEmail() {
        return email;
    }

    public String getLoginType() {
        return loginType;
    }

    public int getLevel() {
        return level;
    }

    public int getHintsLeft() {
        return hintsLeft;
    }

    public int getGoldLeft() {
        return goldLeft;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder("Name : ");
        ret.append(this.name + "\n");
        ret.append("ID : " + this.id + "\n");
        ret.append("UserRole : " + this.userRole + "\n");
        ret.append("EMail : " + this.email +"\n");
        ret.append("LoginType : " + this.loginType+"\n") ;
        ret.append("Level : " + this.level +"\n");
        ret.append("HintsLeft : " + this.hintsLeft +"\n");
        ret.append("Gold Left : " + this.goldLeft + "\n");
        ret.append("Avatar path : " + this.avatarPath  + "\n");

        return ret.toString();
    }
}