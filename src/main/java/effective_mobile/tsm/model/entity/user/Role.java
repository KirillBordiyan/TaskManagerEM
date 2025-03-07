package effective_mobile.tsm.model.entity.user;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Role implements Serializable {
    USER, ADMIN;
}
