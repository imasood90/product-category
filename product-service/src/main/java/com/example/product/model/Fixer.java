package com.example.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Map;


public class Fixer {

    private final Boolean success;
    private final Long timestamp;
    private final String base;
    private final Date date;
    private final Map<String, Double> rates;

    public Fixer(@JsonProperty("success") Boolean success, @JsonProperty("timestamp") Long timestamp,
                 @JsonProperty("base") String base, @JsonProperty("date") Date date,
                 @JsonProperty("rates") Map<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Fixer)) return false;

        Fixer fixer = (Fixer) o;

        return new EqualsBuilder()
                .append(success, fixer.success)
                .append(timestamp, fixer.timestamp)
                .append(base, fixer.base)
                .append(date, fixer.date)
                .append(rates, fixer.rates)
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(success)
                .append(timestamp)
                .append(base)
                .append(date)
                .append(rates)
                .toHashCode();
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("timestamp", timestamp)
                .append("base", base)
                .append("date", date)
                .append("rates", rates)
                .toString();
    }
}

