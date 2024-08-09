package com.handson.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.handson.basic.util.AWSService;
import com.handson.basic.util.Dates;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SqlResultSetMapping(name = "StudentOut")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentOut {

    public Collection<StudentGrade> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(Collection<StudentGrade> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getSatscore() {
        return satscore;
    }

    public void setSatscore(Integer satscore) {
        this.satscore = satscore;
    }

    public Double getGraduationscore() {
        return graduationscore;
    }

    public void setGraduationscore(Double graduationscore) {
        this.graduationscore = graduationscore;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<StudentGrade> studentGrades = new ArrayList<>();

    @Id
    private Long id;

    private Date createdat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdat")
    public LocalDateTime calcCreatedAt() {
        return Dates.atLocalTime(createdat);
    }

    private String fullname;
    private Date birthdate;

    private Double avgscore;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("birthdate")
    public LocalDateTime calcBirthDate() {
        return Dates.atLocalTime(birthdate);
    }

    private Integer satscore;

    public static StudentOut of(Student student, AWSService awsService) {
        StudentOut res = new StudentOut();
        res.id = student.getId();
        res.createdat = student.getCreatedAt();
        res.fullname = student.getFullname();
        res.birthdate = student.getBirthDate();
        res.satscore = student.getSatScore();
        res.graduationscore = student.getGraduationScore();
        res.phone = student.getPhone();
        res.profilepicture = awsService.generateLink(student.getProfilePicture());
        res.avgscore = null;
        return res;
    }

    private Double graduationscore;

    private String phone;
    private String profilepicture;

    public Integer getSatScore() {
        return satscore;
    }

    public Double getGraduationScore() {
        return graduationscore;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public String getFullname() {
        return fullname;
    }

    public Date getBirthdate() {
        return birthdate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvgscore(Double avgscore){
        this.avgscore = avgscore;
    }
    public Double getAvgscore(){return avgscore; }



    public String getPhone() {
        return phone;
    }

    public String getProfilePicture() {
        return profilepicture;
    }


}