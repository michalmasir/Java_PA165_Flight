package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Steward;
import java.util.List;

    /**
     * This interfaces specifies the methods that a {@link cz.muni.fi.PA165.flight.entity.Steward} data access object
     * must implement.
     *
     * @author  Michal Hruby
     */

    public interface StewardDAO {

        /**
         * Returns list of all stewards.
         *
         * @return list of all stewards
         */
        public List<Steward> getAllStewards();

        /**
         * Gets Steward with given id.
         *
         * @param id id of wanted Steward
         * @return wanted Steward or null if the Steward does not exist
         */

        public Steward getStewardById(long id );

        /**
         * Deletes given Steward.
         *
         * @param steward Steward to be deleted
         */

        public void deleteSteward(Steward steward);

        /**
         * Saves given Steward.
         *
         * @param steward Steward to be saved
         */

        public void addSteward(Steward steward);

        /**
         * Updates given Steward.
         *
         * @param steward Steward to be updated
         */

        public void updateSteward(Steward steward);


          }


