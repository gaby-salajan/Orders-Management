package Model;

public class Client {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;

    /**
     * Constructor with parameters
     * @param first_name client's first name
     * @param last_name client's last name
     * @param address client's address
     * @param email client's email
     */
    public Client(String first_name, String last_name, String address, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
    }

    /**
     * Constructor for empty Client
     */
    public Client() {
        this.id = 0;
        this.first_name = "";
        this.last_name = "";
        this.address = "";
        this.email = "";
    }

    /**
     * Sets the client's id
     * @param id id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the client's id
     * @return client id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns client's first name
     * @return client first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets client's first name
     * @param first_name first name to be set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Returns client's last name
     * @return client last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets client's last name
     * @param last_name last name to be set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Returns client's address
     * @return client address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets client's address
     * @param address address to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns client's email
     * @return client email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets client's email
     * @param email email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the client's data as a String
     * @return client data as String
     */
    @Override
    public String toString() {
        return id +") "+first_name+ ", "+last_name+", "+address+", "+email;
    }
}
