export interface Ubicaciones {
    error: boolean;
    msg:   string;
    data:  Pais[];
}


export interface Pais {
    iso2:    string;
    iso3:    string;
    country: string;
    cities:  string[];
}