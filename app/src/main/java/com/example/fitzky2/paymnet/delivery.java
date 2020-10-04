package com.example.fitzky2.paymnet;



    public class delivery {

        String id;
        String name;
        String  contact;
        String postalcode;
        String address;

        public delivery() {
        }
        public delivery(String id, String name, String contact, String postalcode, String address) {
            this.id = id;
            this.name = name;
            this.contact = contact;
            this.postalcode = postalcode;
            this.address = address;

        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }



    }

