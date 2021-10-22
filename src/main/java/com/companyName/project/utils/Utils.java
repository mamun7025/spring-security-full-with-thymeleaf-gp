package com.companyName.project.utils;

public class Utils {

    public static < T > boolean isEmpty( T pVar ) {

        if ( pVar == null ) {
            return true;
        }

        if ( pVar instanceof String ) {
            return ( ( String ) pVar ).trim().length() == 0;
        }

        if ( pVar instanceof Integer ) {
            return ( ( Integer ) pVar ) == -1;
        }

        if ( pVar instanceof Long ) {
            return ( ( Long ) pVar ) == -1;
        }
        return false;

    }

    public static < T > boolean isNotEmpty( T pVar ) {

        return !isEmpty( pVar );
    }

    public static < T > boolean isNotNull( T pVar ) {

        return pVar != null;
    }



}
