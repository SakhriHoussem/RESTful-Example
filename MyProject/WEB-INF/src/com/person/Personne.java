package com.person;
public class Personne {
    public int id;
    public String nom;
    public String prenom;
    public int age;
    public Personne(int myid,String mynom,String myprenom, int myage){
      this.id = myid;
      this.nom = mynom;
      this.prenom = myprenom;
      this.age = myage;
    }
    public void setId(int myid){
      this.id = myid;
    }
    public void setNom(String myNom){
      this.nom = myNom;
    }
    public void setPrenom(String myPrenom){
      this.prenom = myPrenom;
    }
    public void setAge(int myAge){
      this.age = myAge;
    }
  }
