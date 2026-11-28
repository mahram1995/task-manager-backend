package com.mislbd.sprint.entity;

public final class Enums {
    private Enums() {
    }

    public enum DevelopmentStatus {PENDING, IN_PROGRESS, ON_HOLD, COMPLETED}
    public enum QAStatus {PENDING, IN_PROGRESS, ON_HOLD, COMPLETED}

    public enum Priority {LOW, NORMAL, HIGH, CRITICAL}

    public enum DeliveryStatus {PENDING, DELIVERED, DELAYED}
    public enum UserCategory {SCRUM_MASTER, TEAM_MEMBER, PRODUCT_OWNER, VIEWER}
}
