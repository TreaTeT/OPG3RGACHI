public class Ucet {

        private int ID_uctu;
        private int ID;
        private int stav;

        public Ucet(int ID_uctu, int ID, int stav){
            this.ID_uctu = ID_uctu;
            this.ID = ID;
            this.stav = stav;


        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setID_uctu(int ID_uctu) {
            this.ID_uctu = ID_uctu;
        }

        public void setStav(int stav) {
            this.stav = stav;
        }

        public int getID() {
            return ID;
        }

        public int getID_uctu() {
            return ID_uctu;
        }

        public int getStav() {
            return stav;
        }
    }

