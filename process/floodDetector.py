
from datetime import date, timedelta
import numpy as np
import pylab as pl
import os

class floodDetector():
    def __init__(self):
        self.__bday   = date.today()
        self.__coords = {'cart':{}, 'geog':{}}
        self.__flood  = np.array([])
        self.__dbuff  = []
        self.__vbuff  = []
        self.__mbuff  = []
        self.__sbuff  = []

        self.__alarm_on  = False
        self.__threshold = 10
        self.__latitude  = 0
        self.__longitude = 0

    def set_alarm_threshold(self,threshold):
        self.__threshold = threshold

    def set_init_date(self, year, month, day):
        self.__bday = date(year=year, month=month, day=day)

    def coords_to_index(self):
        self.__coords['cart']['lat_low']  = int((50-self.__coords['geog']['lat_low'])*8 )
        self.__coords['cart']['long_low'] = int((self.__coords['geog']['long_low']+127.25)*8)
        self.__coords['cart']['lat_up']   = int((50-self.__coords['geog']['lat_up'])*8 )
        self.__coords['cart']['long_up']  = int((self.__coords['geog']['long_up']+127.25)*8)

    def set_region(self, latl, longl, latu, longu):
        self.__coords['geog']['lat_low']  = latl
        self.__coords['geog']['long_low'] = longl
        self.__coords['geog']['lat_up']   = latu
        self.__coords['geog']['long_up']  = longu

        self.coords_to_index()

    def load_data(self, path2file):
        if os.path.isfile(path2file):
            self.__flood = np.fromfile(path2file, dtype=np.float32)
            self.__flood = self.__flood.reshape((800,2458))
            return True
        else:
            return False

    def proc(self,mapa):
        datos  = mapa.reshape(1,mapa.size)
        datos  = datos[datos>0.0]
        
        if not datos.any():
            self.__dbuff.append(0)
            self.__sbuff.append(0)
        else:
            self.__dbuff.append(datos.mean())
            self.__vbuff.append(datos.std())
            self.__mbuff.append(datos.max())
            self.__sbuff.append(100*(1.0*datos.size/mapa.size))


    def find_max_position(self,mapa, mmax):
        i,j  = np.where(mapa==mmax);
        return ((50-i[0]*0.125),(0.125*j[0]-127.15))

    def detect(self):

        for index in range(0,24,3):
            path = './dataset/'
            path += 'Flood_byStor_'
            path += self.__bday.strftime('%Y%m%d')
            path += '{0:02d}'.format(index)
            path += '.bin'

            if self.load_data(path):

                lat0 = self.__coords['cart']['lat_low']
                lat1 = self.__coords['cart']['lat_up']
                lon0 = self.__coords['cart']['long_low']
                lon1 = self.__coords['cart']['long_up']

                self.proc(self.__flood[lat1:lat0+1,lon0:lon1+1])

                if self.__mbuff[-1] >= self.__threshold:
                    self.__alarm_on = True
                    self.__latitude, self.__longitude = self.find_max_position(self.__flood[lat1:lat0+1,lon0:lon1+1],self.__mbuff[-1])
                else:
                    self.__alarm_on = False

                


        self.__bday = self.__bday + timedelta(days=1)

    def get_alarm_status(self):
        return self.__alarm_on

    def get_max_position(self):
        return self.__latitude, self.__longitude
    
    def get_dbuff(self):
        return self.__dbuff

    def get_vbuff(self):
        return self.__vbuff

    def get_mbuff(self):
        return self.__mbuff

    def get_sbuff(self):
        return self.__sbuff
