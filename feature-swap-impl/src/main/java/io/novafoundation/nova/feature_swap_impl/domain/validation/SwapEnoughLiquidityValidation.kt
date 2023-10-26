package io.novafoundation.nova.feature_swap_impl.domain.validation

import io.novafoundation.nova.common.validation.ValidationStatus
import io.novafoundation.nova.common.validation.valid
import io.novafoundation.nova.common.validation.validationError
import io.novafoundation.nova.feature_swap_api.domain.model.SwapQuote
import io.novafoundation.nova.feature_swap_api.domain.swap.SwapService
import io.novafoundation.nova.feature_swap_impl.domain.validation.SwapValidationFailure.NotEnoughLiquidity

class SwapEnoughLiquidityValidation(
    private val quoteRetriever: suspend (SwapValidationPayload) -> Result<SwapQuote>
) : SwapValidation {

    override suspend fun validate(value: SwapValidationPayload): ValidationStatus<SwapValidationFailure> {
        val quoteResult = quoteRetriever(value)
        if (quoteResult.isFailure) {
            return NotEnoughLiquidity.validationError()
        }

        return valid()
    }
}
