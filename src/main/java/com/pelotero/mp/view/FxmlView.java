package com.pelotero.mp.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    BOOKING {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("booking.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Booking.fxml";
        }
    },
    MENUADMIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuadmin.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuAdmin.fxml";
        }
    },
    CLIENT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("client.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Client.fxml";
        }
    },
    TOPIC {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("topic.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Topics.fxml";
        }
    },
    PRODUCT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("product.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Stock.fxml";
        }
    },
    COMBO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("combo.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Combo.fxml";
        }
    },
    DUTY {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("duty.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Duty.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
