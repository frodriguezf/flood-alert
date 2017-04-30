from floodDetector import floodDetector as fd
import matplotlib.pyplot as plt

det0 = fd()
det0.set_init_date(2013,3,31)
det0.set_region(-36.35, -66.20, -33.83, -59.58)
det0.detect()
det0.detect()
det0.detect()
det0.detect()
det0.detect()




plt.figure()
plt.plot(det0.get_dbuff(),label='mean')
plt.plot(det0.get_vbuff(),label='std')
plt.plot(det0.get_mbuff(),label='max')
plt.grid(True)
plt.legend(loc=0)

plt.figure()
plt.plot(det0.get_sbuff())
plt.grid(True)

plt.show()
