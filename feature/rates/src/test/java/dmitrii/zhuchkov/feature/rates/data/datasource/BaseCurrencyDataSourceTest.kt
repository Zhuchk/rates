package dmitrii.zhuchkov.feature.rates.data.datasource

import dmitrii.zhuchkov.feature.rates.domain.entity.CurrencyRate
import org.junit.Assert
import org.junit.Test

/**
 * Created by Dmitrii on 06.01.2020
 *
 */
class BaseCurrencyDataSourceTest {

	private val dataSource: BaseCurrencyDataSource = BaseCurrencyDataSourceImpl()

	@Test
	fun `WHEN set value EXPECT same value returns`() {
		val testData = CurrencyRate(
			code = "",
			value = 1.0
		)

		dataSource.set(testData)

		val actualResult = dataSource.get()

		Assert.assertEquals(testData, actualResult)
	}
}