package se.lexicon;

import java.util.Arrays;

/**
 * The NameRepository class provides methods to manage a list of names.
 * It offers functionalities such as adding, removing, finding, and updating names.
 */
public class NameRepository {

    private static String[] names = new String[0]; // element format should be ["firstName lastName", "firstName lastName"]


    /**
     * Retrieves the current size of the names array.
     *
     * @return The number of elements in the names array.
     */
    public static int getSize() {
        return names.length;
    }


    /**
     * Sets the names array to the provided array of names & it should replace all existing names.
     *
     * @param names The array of names to set.
     */
    public static void setNames(final String[] names) {
        NameRepository.names = names;
    }


    /**
     * Clears the names array by creating a new empty array.
     */
    public static void clear() {
        names = new String[0];
    }


    /**
     * Returns all names in a new array (Retrieves a copy of the names array).
     *
     * @return A new array containing all elements from the names array.
     */
    public static String[] findAll() {
        return Arrays.copyOf(names, names.length);
    }

    /**
     * Finds a name that matches the given fullName case-insensitively.
     *
     * @param fullName The full name to search for.
     * @return The matching name if found; otherwise, null.
     */
    public static String find(final String fullName) {
        for(String name : names) {
            if(name.equalsIgnoreCase(fullName)) {
                return name;
            }
        }
        return null;
    }

    /**
     * Adds a new fullName to the names array if it doesn't already exist.
     *
     * @param fullName The full name to add.
     * @return True if the fullName is added successfully; false if it already exists.
     */
    public static boolean add(final String fullName) {
        /// Adding a new array
        Arrays.sort(names);
        if (Arrays.binarySearch(names, fullName) < 0) {
            names = Arrays.copyOf(names,names.length+1);
            names[names.length-1] = fullName;
            return true;
        }
        return false;
    }


    /**
     * Find all names that match the given firstName.
     *
     * @param firstName The first name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByFirstName(final String firstName) {
        String[] foundNames = new String[0];
        for (String name : names) {
            //if (name.startsWith(firstName)) {
            if (name.split(" ")[0].equalsIgnoreCase(firstName)) {
                foundNames = Arrays.copyOf(foundNames, foundNames.length+1);
                foundNames[foundNames.length-1] = name;
            }
        }
        return foundNames;
    }


    /**
     * Find all names that match the given lastName.
     *
     * @param lastName The last name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByLastName(final String lastName) {
        String[] foundNames = new String[0];
        for (String name : names) {
            if (name.split(" ")[1].equalsIgnoreCase(lastName)) {
                foundNames = Arrays.copyOf(foundNames, foundNames.length+1);
                foundNames[foundNames.length-1] = name;
            }
        }
        return foundNames;
    }


    /**
     * Updates a name in the names array from the original name to the updated name.
     *
     * @param original    The original name to update.
     * @param updatedName The updated name to set.
     * @return True if the name is updated successfully; false if the updated name already exists or the original name is not found.
     */
    public static boolean update(final String original, final String updatedName) {
        Arrays.sort(names);
        int index = Arrays.binarySearch(names, original);
        // if original don't exist or updated name already exists
        if (index < 0 || Arrays.binarySearch(names, updatedName) >= 0) {
            return false;
        } else {
            names[index] = updatedName;
            return true;
        }
    }


    /**
     * Removes a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean remove(final String fullName) {
        // create new array
        String[] newArray = new String[names.length];
        // copy all elements except "fullName"
        for (int i=0, j=0; i < names.length; i++) {
            // if fullName (to remove) is not found, continue copying
            if (!names[i].equalsIgnoreCase(fullName)) {
                // copy element
                newArray[j] = names[i];
                // increment newArray index
                j++;
            }
        }
        boolean isRemoved = newArray[newArray.length-1] == null;
        // make length of newArray -1
        if (isRemoved) {
            newArray = Arrays.copyOf(newArray, newArray.length-1);
        }
        // update names
        setNames(newArray);
        // return if removed or not
        return isRemoved;
    }

    /*
    public void method() {
        boolean found = false;

        // Temporary array to hold updated names
        String[] newArray = new String[0];

        for (String name : names) {
            if (!name.equalsIgnoreCase(fullName)) {
                // Keep this name
                newArray = Arrays.copyOf(newArray, newArray.length + 1);
                newArray[newArray.length - 1] = name;
            } else {
                // We found the one to remove
                found = true;
            }
        }

        if (found) {
            setNames(newArray); // ✅ Now we update the main array
            return true;
        }

        return false; // Nothing was removed
    }
*/

}