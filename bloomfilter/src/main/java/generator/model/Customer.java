package generator.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 */

@TableName("t_customer")
public class Customer implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String cname;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private Byte sex;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date birth;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     */
    public String getCname() {
        return cname;
    }

    /**
     *
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     *
     */
    public Integer getAge() {
        return age;
    }

    /**
     *
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     */
    public Byte getSex() {
        return sex;
    }

    /**
     *
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     *
     */
    public Date getBirth() {
        return birth;
    }

    /**
     *
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Customer() {
    }

    public Customer(Integer id, String cname, Integer age, String phone) {
        this.id = id;
        this.cname = cname;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCname() == null ? other.getCname() == null : this.getCname().equals(other.getCname()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getBirth() == null ? other.getBirth() == null : this.getBirth().equals(other.getBirth()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCname() == null) ? 0 : getCname().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getBirth() == null) ? 0 : getBirth().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cname=").append(cname);
        sb.append(", age=").append(age);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", birth=").append(birth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
