/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeans.saas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "REVIEW")
@XmlRootElement





@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByReviewId", query = "SELECT r FROM Review r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "Review.findByUserName", query = "SELECT r FROM Review r WHERE r.userName = :userName"),
    @NamedQuery(name = "Review.findByDate", query = "SELECT r FROM Review r WHERE r.date = :date"),
    @NamedQuery(name = "Review.findByLocation", query = "SELECT r FROM Review r WHERE r.location = :location"),
    @NamedQuery(name = "Review.findByComment", query = "SELECT r FROM Review r WHERE r.comment = :comment"),
    @NamedQuery(name = "Review.findByScore", query = "SELECT r FROM Review r WHERE r.score = :score"),
    @NamedQuery(name = "Review.findByAvg",query = "SELECT avg(r.score) FROM Review r WHERE r.movieId.movieName = :movieName"),
    @NamedQuery(name = "Review.fingByTotalUser",query="SELECT count(r.userName) FROM Review r WHERE r.movieId.movieName = :movieName"),
    @NamedQuery(name = "Review.findByUserDate", query="SELECT r.date FROM Review r WHERE r.movieId.movieName = :movieName"),
    @NamedQuery(name = "Review.findByReviewUserName",query = "SELECT r.userName FROM Review r WHERE r.movieId.movieName = :movieName"),
    @NamedQuery(name = "Review.findByReviewUserLocation", query = "SELECT r.location FROM Review r WHERE r.movieId.movieName = :movieName"),
    @NamedQuery(name = "Review.findByUserComment", query ="SELECT r.comment FROM Review r WHERE r.movieId.movieName = :movieName")
})





public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REVIEW_ID")
    private Short reviewId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "SCORE")
    private Integer score;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
    @ManyToOne(optional = false)
    private Movie movieId;

    public Review() {
    }

    public Review(Short reviewId) {
        this.reviewId = reviewId;
    }

    public Review(Short reviewId, String userName, Date date, String location, String comment) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.date = date;
        this.location = location;
        this.comment = comment;
    }

    public Short getReviewId() {
        return reviewId;
    }

    public void setReviewId(Short reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "netbeans.saas.Review[ reviewId=" + reviewId + " ]";
    }
    
}
