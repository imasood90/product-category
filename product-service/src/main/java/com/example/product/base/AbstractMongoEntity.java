package com.example.product.base;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractMongoEntity<UUID extends Serializable> {

    @Id
    private UUID id;

    private String createdBy;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdDate;

    private String lastModifiedBy;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date lastModifiedDate;


    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Get createdBy
     *
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get created date
     *
     * @return Date
     */
    public Date getCreatedDate() {
        return createdDate != null ? new Date(createdDate.getTime()) : null;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate != null ? new Date(createdDate.getTime()) : null;
    }

    /**
     * Get last modified by
     *
     * @return LastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Get Last Modified date
     *
     * @return lastModifiedDate
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate != null ? new Date(lastModifiedDate.getTime()) : null;
    }

    /**
     * @param lastModifiedDate the lastModifiedDate to set
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate != null
                ? new Date(lastModifiedDate.getTime()) : null;
    }

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

}
