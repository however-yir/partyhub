package org.example.djxt.dto;

public class StarBucketCount {
    private String key;
    private String label;
    private long count;

    public StarBucketCount() {
    }

    public StarBucketCount(String key, String label, long count) {
        this.key = key;
        this.label = label;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
