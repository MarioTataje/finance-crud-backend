package com.financecrudbackend.models;

public enum State {
    ACTUAL{
        @Override
        public String response(){
            return "Actual";
        }
        @Override
        public int id(){
            return 0;
        }
    },
    REVISED{
        @Override
        public String response(){
            return "Revised";
        }
        @Override
        public int id(){
            return 1;
        }
    },
    ESTIMATED{
        @Override
        public String response(){
            return "Estimated";
        }
        @Override
        public int id(){
            return 2;
        }
    };

    public abstract String response();
    public abstract int id();
}
