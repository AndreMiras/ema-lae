/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Parameter;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 *
 * @author pc
 */
@Entity
public class UserProfile implements Serializable, WithPrimaryKey {

    //TODO: check this code
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Users user;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @ManyToOne
    private Promotion promotion;
    public enum Gender { Male, Female }
    @Column
    private Gender gender;
    public enum Type
        { Apprentice, InternshipSupervisor, SupervisingTeacher, Other }
    @Column
    private Type userProfileType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contract contract;
    @Column
    private String photoPath;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "teacher")
    private Set<CourseSession> courseSessions;


    public UserProfile()
    {
        this.courseSessions = new HashSet<CourseSession>();
    }

    public UserProfile(Users user)
    {
        this();
        this.user = user;
    }

    public UserProfile(Users user, Type userProfileType) {
        this(user);
        this.userProfileType = userProfileType;
    }

    
    public Integer getUserId()
    {
        return userId;
    }

    public Users getUser()
    {
        return user;
    }


    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return age computed from birthdate
     */
    public Integer getAge()
    {
        if(birthDate != null)
        {
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            if (dob.after(now))
            {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            int age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1)
            {
                age--;
            } else if (month1 == month2)
            {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1)
                {
                    age--;
                }
            }
            return age;
        }
        else
        {
            return 0;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUser(Users user)
    {
        this.user = user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return getFullName();
    }

    public Type getUserProfileType() {
        return userProfileType;
    }

    public void setUserProfileType(Type userProfileType) {
        this.userProfileType = userProfileType;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isApprentice(){
        return userProfileType == Type.Apprentice;
    }

    public boolean isInternshipSupervisor(){
        return userProfileType == Type.InternshipSupervisor;
    }

    public boolean isSupervisingTeacher(){
        return userProfileType == Type.SupervisingTeacher;
    }

    public String getPhotoPath()
    {
        // TODO: this is cheating, the image should really get downloaded
        // back to the client
        if (photoPath == null)
        {
            if (gender == Gender.Female)
            {
                return "src/emalaedesktopapplication/resources/woman-icon.png";
            }
            else
            {
                return "src/emalaedesktopapplication/resources/man-icon.png";
            }
        }
        return photoPath;
    }

    public void setPhotoPath(String photo)
    {
        this.photoPath = photo;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getTitle()
    {
        if (gender == Gender.Female)
        {
            return "Mme.";
        }
        else
        {
            return "M.";
        }
    }

    public Set<CourseSession> getCourseSessions()
    {
        return courseSessions;
    }

    public void setCourseSessions(Set<CourseSession> sessions)
    {
        this.courseSessions = sessions;
        for (CourseSession session: sessions)
        {
            session.setTeacher(this);
        }
    }

    public boolean addCourseSession(CourseSession courseSession){
        return this.courseSessions.add(courseSession);
    }

    public boolean ownsSession(CourseSession session)
    {
        CourseSession tmpSession;
        Iterator<CourseSession> it = this.getCourseSessions().iterator();
        boolean foundSession = false;
        while(!foundSession && it.hasNext())
        {
            tmpSession = it.next();
            if (session.getSessionId() != null)
            {
                foundSession = session.getSessionId().equals(
                        tmpSession.getSessionId());
            }
            else
                foundSession = (session.getTeacher().equals(tmpSession.getTeacher())&&(session.getTeacher().equals(session.getTeacher())));
        }
        return foundSession;
    }

    public Serializable getPrimaryKey()
    {
        return userId;
    }
}
