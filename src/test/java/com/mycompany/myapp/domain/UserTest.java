package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String EMAIL = "EMAIL";
    private static final boolean ACTIVATED = true;
    private static final String LANG_KEY = "LANG_KEY";
    private static final String IMAGE_URL = "IMAGE_URL";
    private static final String ACTIVATION_KEY = "ACTIVATION_KEY";
    private static final String RESET_KEY = "RESET_KEY";
    private static final Instant RESET_DATE = Instant.now();
    private static final String CREATED_BY = "CREATED_BY";
    private static final Instant CREATED_DATE = Instant.now();
    private static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
    private static final Instant LAST_MODIFIED_DATE = Instant.now();

    @Mock
    private Authority authority;

    @InjectMocks
    private User underTest;

    @Test
    void getId() {}

    @Test
    void setId() {}

    @Test
    void getLogin() {}

    @Test
    void setLogin() {}

    @Test
    void getPassword() {}

    @Test
    void setPassword() {}

    @Test
    void getFirstName() {}

    @Test
    void setFirstName() {}

    @Test
    void getLastName() {}

    @Test
    void setLastName() {}

    @Test
    void getEmail() {}

    @Test
    void setEmail() {}

    @Test
    void getImageUrl() {}

    @Test
    void setImageUrl() {}

    @Test
    void isActivated() {}

    @Test
    void setActivated() {}

    @Test
    void getActivationKey() {}

    @Test
    void setActivationKey() {}

    @Test
    void getResetKey() {}

    @Test
    void setResetKey() {}

    @Test
    void getResetDate() {}

    @Test
    void setResetDate() {}

    @Test
    void getLangKey() {}

    @Test
    void setLangKey() {}

    @Test
    void getAuthorities() {}

    @Test
    void setAuthorities() {}

    @Nested
    class WhenGettingId {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingLogin {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingPassword {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingFirstName {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingLastName {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingEmail {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingImageUrl {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenCheckingIfIsActivated {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingActivationKey {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingResetKey {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingResetDate {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingLangKey {

        @BeforeEach
        void setup() {}
    }

    @Nested
    class WhenGettingAuthorities {

        @BeforeEach
        void setup() {}
    }
}
